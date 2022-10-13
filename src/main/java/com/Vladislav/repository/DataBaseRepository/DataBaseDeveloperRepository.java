package com.Vladislav.repository.DataBaseRepository;

import com.Vladislav.model.Developer;
import com.Vladislav.model.Skill;
import com.Vladislav.model.Specialty;
import com.Vladislav.repository.DeveloperRepository;
import com.Vladislav.repository.SkillRepository;
import com.Vladislav.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseDeveloperRepository implements DeveloperRepository {
    private final Connection connection = ConnectionManager.openConnection();

    @Override
    public Developer getById(Integer id) {
        Developer developer;

        String SQL = """
                SELECT * FROM developers
                WHERE id = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Skill> skills = getSkills(id);
            Specialty specialty = getSpecialty(id);
            int devId = 0;
            String firstName = null;
            String lastName = null;

            if (resultSet.next()) {
                devId = resultSet.getInt("id");
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                int checkStatus = resultSet.getInt("status");
                if (checkStatus == 0) {
                    return null;
                }
                developer = new Developer(firstName, lastName, skills, specialty);
                developer.setId(devId);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        String SQL = "SELECT id FROM developers";
        List<Integer> developerCounter = new ArrayList<>();
        List<Developer> developerList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                developerCounter.add(resultSet.getInt("id"));
            }
            for (Integer i : developerCounter) {
                developerList.add(getById(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return developerList.isEmpty() ? null : developerList;
    }

    @Override
    public Developer save(Developer developer) {
        String SQL = """
                INSERT INTO developers (first_name, last_name, status)
                VALUES(?, ?, 1)
                """;
        String SQL_skill = "INSERT INTO developer_skill (developer_id, skill_id) VALUES (?, ?)";
        String SQL_specialty = "INSERT INTO developer_specialty (developer_id, specialty_id) VALUES (?, ?)";
        int update = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, developer.getFirstName());
            preparedStatement.setString(2, developer.getLastName());
            update = preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int devId = generatedKeys.getInt(1);
                    developer.setId(devId);
                    developer.getSkillList().forEach(x -> setConnectTable(devId, x.getId(), SQL_skill));
                    int specialtyId = developer.getSpecialty().getId();
                    setConnectTable(devId, specialtyId, SQL_specialty);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update == 0 ? null : developer;
    }

    @Override
    public int update(Developer developer, int id) {
        String SQL = "UPDATE developers SET first_name = ?, last_name = ? where id = ?;";
        int update = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, developer.getFirstName());
            preparedStatement.setString(2, developer.getLastName());
            preparedStatement.setInt(3, id);
            update = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }

    @Override
    public int deleteById(Integer id) {
        String SQL = "UPDATE developers" +
                "    SET status = 0" +
                "    WHERE developers.id = ?";
        int update = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            update = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }

    private List<Skill> getSkills(int id) {
        String SQLskill = """
                SELECT id, name FROM 
                skills JOIN developer_skill
                ON (skill_id = id)
                WHERE developer_id = ?;    
                """;
        ArrayList<Skill> skills = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLskill)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String skillName = resultSet.getString("name");
                    int skillId = resultSet.getInt("id");
                    Skill skill = new Skill(skillName);
                    skill.setId(skillId);
                    skills.add(skill);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skills;
    }

    private Specialty getSpecialty(int id) {
        String SQLspecialty = """
                SELECT id, name FROM
                specialty JOIN developer_specialty
                ON (specialty_id = id)
                WHERE developer_id = ?; 
                """;
        Specialty specialty = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLspecialty)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String specialtyName = resultSet.getString("name");
                    int specialtyId = resultSet.getInt("id");
                    specialty = new Specialty(specialtyName);
                    specialty.setId(specialtyId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialty;
    }

    private void setConnectTable(int devId, int itemId, String SQL_query) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_query)) {
            preparedStatement.setInt(1, devId);
            preparedStatement.setInt(2, itemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
