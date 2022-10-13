package com.Vladislav.repository.DataBaseRepository;

import com.Vladislav.model.Skill;
import com.Vladislav.repository.SkillRepository;
import com.Vladislav.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseSkillRepository implements SkillRepository {
    private final Connection connection = ConnectionManager.openConnection();

    @Override
    public Skill getById(Integer id) {
        String SQL = "SELECT * FROM skills WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Skill skill = null;
            while (resultSet.next()) {
                String skillname = resultSet.getString("name");
                int skillId = resultSet.getInt("id");
                skill = new Skill(skillname);
                skill.setId(skillId);
            }
            return skill;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Skill> getAll() {
        String SQL = "SELECT * FROM skills";
        List<Skill> skillList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String skillName = resultSet.getString("name");
                int skillId = resultSet.getInt("id");
                Skill skill = new Skill(skillName);
                skill.setId(skillId);
                skillList.add(skill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skillList.isEmpty() ? null : skillList;
    }

    @Override
    public Skill save(Skill skill) {
        String SQL = "INSERT INTO skills (name) VALUES (?)";
        int update = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, skill.getName());
            update = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update == 0 ? null : skill;
    }

    @Override
    public int update(Skill skill, int id) {
        String SQL = "UPDATE skills SET name = ? WHERE id = ?";
        int update = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setInt(2, id);

            update = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }

    @Override
    public int deleteById(Integer id) {
        String SQL = "DELETE FROM skills WHERE ID = ?";
        int updates = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            updates = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updates;
    }
}
