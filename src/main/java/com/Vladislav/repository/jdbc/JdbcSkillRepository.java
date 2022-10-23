package com.Vladislav.repository.jdbc;

import com.Vladislav.model.Skill;
import com.Vladislav.repository.SkillRepository;
import com.Vladislav.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcSkillRepository implements SkillRepository {

    private Skill convertResultSetToSkill(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        int id = resultSet.getInt("id");
        return new Skill(id, name);
    }

    @Override
    public Skill getById(Integer id) {
        String SQL = "SELECT * FROM skills WHERE id = ?";
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Skill skill = null;
            while (resultSet.next()) {
                skill = convertResultSetToSkill(resultSet);
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
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                skillList.add(convertResultSetToSkill(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skillList;
    }

    @Override
    public Skill save(Skill skill) {
        String SQL = "INSERT INTO skills (name) VALUES (?)";
        Skill addedSkill = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatementWithGeneratedKeys(SQL)) {
            preparedStatement.setString(1, skill.getName());
            int update = preparedStatement.executeUpdate();
            if(update > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                 addedSkill = new Skill(generatedKeys.getInt(1), skill.getName());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return addedSkill;
    }

    @Override
    public Skill update(Skill skill) {
        String SQL = "UPDATE skills SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQL)) {
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setInt(2, skill.getId());
            int update = preparedStatement.executeUpdate();
            return update > 0 ? skill : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        String SQL = "DELETE FROM skills WHERE ID = ?";
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
