/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * @author Phuong
 */
public class ProductModel {

    ObservableList<Product> list = FXCollections.observableArrayList();

    public boolean save(Product p) {
        try {
            String sql = "insert into products (name, image, price) values (?,?,?)";
            PreparedStatement ps = ConnectionHandle.getIntance().getConnection().prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getImage());
            ps.setLong(3, p.getPrice());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ObservableList<Product> query() {
        try {
            String sql = "select * from products";
            PreparedStatement ps = ConnectionHandle.getIntance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String image = rs.getString("image");
                int price = rs.getInt("price");
                list.addAll(new Product(name, image, price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String queryImage(String img) {
        try {
            String sql = "select * from products where image = ?";
            PreparedStatement ps = ConnectionHandle.getIntance().getConnection().prepareStatement(sql);
            ps.setString(1, img);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return img;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ProductModel model = new ProductModel();
        Product p = new Product();
        model.query();
    }
}
