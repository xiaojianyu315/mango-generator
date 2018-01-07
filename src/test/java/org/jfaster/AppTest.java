package org.jfaster;

import org.jfaster.generator.MangoGenerator;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String[] args) {
        MangoGenerator mg = new MangoGenerator(
                "jdbc:mysql://localhost:3306/lc",
                "root",
                "root",
                "lc",
                "",
                "/Users/xiaojianyu/Downloads/1/");
        mg.generator();
    }
}
