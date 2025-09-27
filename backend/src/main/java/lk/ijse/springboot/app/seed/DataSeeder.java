package com.yourcompany.pos.seed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Configuration
public class DataSeeder {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    /** Use INSERT IGNORE so duplicates are silently skipped (requires UNIQUE/PK). */
    @Bean
    ApplicationRunner seed(JdbcTemplate jdbc) {
        return args -> {
            log.info("Seeding baseline data if missing…");

            // --- users --------------------------------------------------------
            try {
                jdbc.update("INSERT IGNORE INTO `user` (username, password) VALUES (?, ?)",
                        "username", "password");
            } catch (Exception e) {
                log.warn("Could not insert into `user` table. Check table/columns match.", e);
            }

            // --- customers ----------------------------------------------------
            List<Object[]> customers = List.of(
                    new Object[] { "C001","Panadura","Danapala" },
                    new Object[] { "C002","Matara","Gunapala" },
                    new Object[] { "C003","Galle","Somapala" },
                    new Object[] { "C004","Galle","Siripala" },
                    new Object[] { "C005","Panadura","Jinadasa" },
                    new Object[] { "C006","Kalutara","Sepalika" },
                    new Object[] { "C007","Ambalangoda","Jinasena" },
                    new Object[] { "C008","Baddegama","Somadasa" },
                    new Object[] { "C009","Moratuwa","Danasiri" },
                    new Object[] { "C010","Kandy","Somasiri" }
            );
            try {
                jdbc.batchUpdate(
                        "INSERT IGNORE INTO customer (id, address, name) VALUES (?, ?, ?)",
                        customers
                );
            } catch (Exception e) {
                log.warn("Could not insert into customer. Check table/columns match.", e);
            }

            // --- items --------------------------------------------------------
            List<Object[]> items = List.of(
                    new Object[] { "P001",3000,"Keerisamba Retail",105.00 },
                    new Object[] { "P002",200,"Keerisamba 5Kg ",525.00 },
                    new Object[] { "P003",36,"Keerisamba 10Kg",995.00 },
                    new Object[] { "P004",36,"Keerisamba 50Kg",4100.00 },
                    new Object[] { "P005",6000,"Red Raw Rice",60.00 },
                    new Object[] { "P006",300,"Red Raw Rice 10Kg Pack",560.00 },
                    new Object[] { "P007",80,"Red Raw Rice 50Kg Pack",5230.00 },
                    new Object[] { "P008",130,"White Raw Rice 5Kg Pack",275.00 },
                    new Object[] { "P009",13,"White Raw Rice 50Kg Pack",2600.00 },
                    new Object[] { "P010",83,"Wattana Dhal 500g",90.00 },
                    new Object[] { "P011",40,"Wattana Dhal 1Kg",170.00 },
                    new Object[] { "P012",89,"Mysoor Dhal 500g",90.00 },
                    new Object[] { "P013",59,"Mysoor Dhal 1Kg",180.00 },
                    new Object[] { "P014",39,"Orient Green Gram 500g",118.00 },
                    new Object[] { "P015",12,"Orient Green Gram 1Kg",220.00 },
                    new Object[] { "P016",93,"Anchor F/C Milk powder 450g",220.00 },
                    new Object[] { "P017",40,"Anchor F/C Milk powder 1Kg",580.00 },
                    new Object[] { "P018",33,"Anchor N/F Milk powder 1Kg",560.00 },
                    new Object[] { "P019",33,"Milo Packet 400g",240.00 },
                    new Object[] { "P020",40,"Lipton Ceylon Tea 100g",107.00 }
            );
            try {
                jdbc.batchUpdate(
                        "INSERT IGNORE INTO items (code, qty, description, price) VALUES (?, ?, ?, ?)",
                        items
                );
            } catch (Exception e) {
                log.warn("Could not insert into items. Check table/columns match.", e);
            }

            // quick count to confirm
            try {
                Integer cCount = jdbc.queryForObject("SELECT COUNT(*) FROM customer", Integer.class);
                Integer iCount = jdbc.queryForObject("SELECT COUNT(*) FROM items", Integer.class);
                Integer uCount = jdbc.queryForObject("SELECT COUNT(*) FROM `user`", Integer.class);
                log.info("Seed complete: customers={}, items={}, users={}", cCount, iCount, uCount);
            } catch (Exception e) {
                log.warn("Count queries failed. Verify schema name and permissions.", e);
            }
        };
    }
}
