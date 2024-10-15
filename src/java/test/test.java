/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import dal.ProductDAO;
import dto.ProductDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class test {

    public static void main(String[] args) {
//        OrderDetailDAO o = new OrderDetailDAO();
//        System.out.println(o.findProductById(33));
        ProductDAO dao = new ProductDAO();
        ArrayList<ProductDTO> p = dao.findProductGalleryById(2);
        for(ProductDTO item:p){
            System.out.println(item.toString());
        }
    }

}
