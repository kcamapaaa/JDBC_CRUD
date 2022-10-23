package com.Vladislav.repository.jdbc;

import com.Vladislav.model.Specialty;
import com.Vladislav.repository.SpecialtyRepository;
import com.Vladislav.util.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcSpecialtyRepository implements SpecialtyRepository {

    private Specialty convertResultSetToSpecialty(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        int id = resultSet.getInt("id");
        return new Specialty(id, name);
    }

    @Override
    public Specialty getById(Integer id) {
        String SQL = "SELECT * FROM specialties WHERE id = ?";
        Specialty specialty = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                specialty = convertResultSetToSpecialty(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialty;
    }

    @Override
    public List<Specialty> getAll() {
        String SQL = "SELECT * FROM specialties";
        List<Specialty> specialtyList = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String specialtyName = resultSet.getString("name");
                int id = resultSet.getInt("id");
                Specialty specialty = new Specialty(id, specialtyName);
                specialtyList.add(specialty);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialtyList;
    }

    @Override
    public Specialty save(Specialty specialty) {
        String SQL = "INSERT INTO specialties (name) VALUES (?)";
        Specialty addedSpecialty = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatementWithGeneratedKeys(SQL)) {
            preparedStatement.setString(1, specialty.getName());
            int update = preparedStatement.executeUpdate();
            if(update > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    addedSpecialty = new Specialty(generatedKeys.getInt(1), specialty.getName());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return addedSpecialty;
    }

    @Override
    public Specialty update(Specialty specialty) {
        String SQL = "UPDATE specialties SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQL)) {
            preparedStatement.setString(1, specialty.getName());
            preparedStatement.setInt(2, specialty.getId());
            int update = preparedStatement.executeUpdate();
            return update > 0 ? specialty : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        String SQL = "DELETE FROM specialties WHERE ID = ?";
        try (PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement(SQL)) {
            preparedStatement.setInt(1, id);
            int updates = preparedStatement.executeUpdate();
            return updates > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
