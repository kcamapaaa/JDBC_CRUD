package com.Vladislav.repository.jdbc;

import com.Vladislav.model.Developer;
import com.Vladislav.model.Skill;
import com.Vladislav.model.Specialty;
import com.Vladislav.repository.DeveloperRepository;
import com.Vladislav.repository.SkillRepository;
import com.Vladislav.service.DeveloperService;
import com.Vladislav.util.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDeveloperRepository implements DeveloperRepository {
    private Developer convertResultSetToDeveloper(ResultSet resultSet) throws SQLException {
        int devId = resultSet.getInt("id");
        List<Skill> skills = getSkills(devId);
        Specialty specialty = getSpecialty(devId);
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int checkStatus = resultSet.getInt("status");
        if (checkStatus == 0) {
            return null;
        }
        return new Developer(devId, firstName, lastName, skills, specialty);
    }

    @Override
    public Developer getById(Integer id) {
        String SQL = """
                SELECT * FROM developers
                WHERE id = ?
                """;

        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? convertResultSetToDeveloper(resultSet) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Developer> getAll() {
        String SQL = "SELECT id FROM developers";
        List<Developer> developerList = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                developerList.add(getById(id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return developerList;
    }

    @Override
    public Developer save(Developer developer) {
        String SQL = """
                INSERT INTO developers (first_name, last_name, status, specialty_id)
                VALUES(?, ?, 1, ?)
                """;
        String SQL_skill = "INSERT INTO developer_skill (developer_id, skill_id) VALUES (?, ?)";

        int update;
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatementWithGeneratedKeys(SQL)) {
            preparedStatement.setString(1, developer.getFirstName());
            preparedStatement.setString(2, developer.getLastName());
            preparedStatement.setInt(3, developer.getSpecialty().getId());
            update = preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int devId = generatedKeys.getInt(1);
                    developer.setId(devId);
                    developer.setSpecialty(getSpecialty(devId));
                    developer.getSkillList().forEach(x -> setConnectTable(devId, x.getId(), SQL_skill));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update == 0 ? null : developer;
    }

    @Override
    public Developer update(Developer developer) {
        String SQL = "UPDATE developers SET first_name = ?, last_name = ? where id = ?;";
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQL)) {
            preparedStatement.setString(1, developer.getFirstName());
            preparedStatement.setString(2, developer.getLastName());
            preparedStatement.setInt(3, developer.getId());
            return preparedStatement.executeUpdate() == 0 ? null : developer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        String SQL = """
                UPDATE developers
                SET status = 0
                WHERE id = ?
                """;
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQL)) {
            preparedStatement.setInt(1, id);
            int update = preparedStatement.executeUpdate();
            return update > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Skill> getSkills(int id) {
        String SQLskill = """
                SELECT id, name FROM 
                skills JOIN developer_skill
                ON (skill_id = id)
                WHERE developer_id = ?;    
                """;
        ArrayList<Skill> skills = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQLskill)) {
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
                    SELECT s.id, s.name
                    FROM developers d JOIN specialties s
                    ON d.specialty_id = s.id
                    WHERE d.id = ?
                """;
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQLspecialty)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String specialtyName = resultSet.getString("name");
                    int specialtyId = resultSet.getInt("id");
                    return new Specialty(specialtyId, specialtyName);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setConnectTable(int devId, int itemId, String SQL_query) {
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQL_query)) {
            preparedStatement.setInt(1, devId);
            preparedStatement.setInt(2, itemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
