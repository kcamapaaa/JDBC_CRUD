package com.Vladislav.repository.DataBaseRepository;

import com.Vladislav.model.Skill;
import com.Vladislav.model.Specialty;
import com.Vladislav.repository.SpecialtyRepository;
import com.Vladislav.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseSpecialtyRepository implements SpecialtyRepository {
    private final Connection connection = ConnectionManager.openConnection();
    @Override
    public Specialty getById(Integer id) {
        String SQL = "SELECT * FROM specialty WHERE id = ?";
        Specialty specialty = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String specialtyName = resultSet.getString("name");
                specialty = new Specialty(specialtyName);
                specialty.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialty;
    }

    @Override
    public List<Specialty> getAll() {
        String SQL = "SELECT * FROM specialty";
        List<Specialty> specialtyList = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String specialtyName = resultSet.getString("name");
                int specialtyId = resultSet.getInt("id");
                Specialty specialty = new Specialty(specialtyName);
                specialty.setId(specialtyId);
                specialtyList.add(specialty);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialtyList;
    }

    @Override
    public Specialty save(Specialty specialty) {
        String SQL = "INSERT INTO specialty (name) VALUES (?)";
        int update = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, specialty.getName());
            update = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update == 0 ? null : specialty;
    }

    @Override
    public int update(Specialty specialty, int id) {
        String SQL = "UPDATE specialty SET name = ? WHERE id = ?";
        int update = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, specialty.getName());
            preparedStatement.setInt(2, id);

            update = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }

    @Override
    public int deleteById(Integer id) {
        String SQL = "DELETE FROM specialty WHERE ID = ?";
        int updates = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            updates = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updates;
    }
}
