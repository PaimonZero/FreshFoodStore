/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import dal.CategoryDAO;
import dal.HomePageDAO;
import dal.ProductDAO;

/**
 *
 * @author nguye
 */
public class test {

    public static void main(String[] args) {
        HomePageDAO h = new HomePageDAO();
        CategoryDAO c = new CategoryDAO();
        ProductDAO p = new ProductDAO();
//        System.out.println(h.getProductKhuyenMai());
//        System.out.println(h.getProductBanChay());
//        System.out.println(h.getProductDanhGiaTot());

        System.out.println(c.findByName("Fruits", 0, 4));
        System.out.println(c.sortProductByUporDown("Fruits", "p.unitPrice", "asc", 0, 4));

    }

}
