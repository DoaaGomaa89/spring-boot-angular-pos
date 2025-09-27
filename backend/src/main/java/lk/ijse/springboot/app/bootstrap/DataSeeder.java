package lk.ijse.springboot.app.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);
    private final JdbcTemplate jdbc;

    public DataSeeder(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void run(String... args) {
        log.info("Seeding baseline data if missing…");

        // --- users (NOTE: backticks because user is a MySQL reserved word)
        try {
            jdbc.update("INSERT IGNORE INTO `user` (username, password) VALUES (?, ?)",
                    "username", "password");
            jdbc.update("INSERT IGNORE INTO `user` (username, password) VALUES (?, ?)",
                    "admin", "admin123");
        } catch (Exception e) {
            log.warn("Insert into `user` failed. Check table & columns.", e);
        }

        // --- customers (adjust column order if your table is name/address swapped)
        try {
            jdbc.batchUpdate(
                "INSERT IGNORE INTO customer (id, address, name) VALUES (?, ?, ?)",
                List.of(
                    new Object[]{"C001","Panadura","Danapala"},
                    new Object[]{"C002","Matara","Gunapala"},
                    new Object[]{"C003","Galle","Somapala"},
                    new Object[]{"C004","Galle","Siripala"},
                    new Object[]{"C005","Panadura","Jinadasa"},
                    new Object[]{"C006","Kalutara","Sepalika"},
                    new Object[]{"C007","Ambalangoda","Jinasena"},
                    new Object[]{"C008","Baddegama","Somadasa"},
                    new Object[]{"C009","Moratuwa","Danasiri"},
                    new Object[]{"C010","Kandy","Somasiri"}
                )
            );
        } catch (Exception e) {
            log.warn("Insert into customer failed. Check table & columns.", e);
        }

        // --- items
        try {
            jdbc.batchUpdate(
                "INSERT IGNORE INTO items (code, qty_on_hand, description, unic_price) VALUES (?, ?, ?, ?)",
                List.of(
                    new Object[]{"P001",3000,"Keerisamba Retail",105.00},
                    new Object[]{"P002",200,"Keerisamba 5Kg ",525.00},
                    new Object[]{"P003",36,"Keerisamba 10Kg",995.00},
                    new Object[]{"P004",36,"Keerisamba 50Kg",4100.00},
                    new Object[]{"P005",6000,"Red Raw Rice",60.00},
                    new Object[]{"P006",300,"Red Raw Rice 10Kg Pack",560.00},
                    new Object[]{"P007",80,"Red Raw Rice 50Kg Pack",5230.00},
                    new Object[]{"P008",130,"White Raw Rice 5Kg Pack",275.00},
                    new Object[]{"P009",13,"White Raw Rice 50Kg Pack",2600.00},
                    new Object[]{"P010",83,"Wattana Dhal 500g",90.00},
                    new Object[]{"P011",40,"Wattana Dhal 1Kg",170.00},
                    new Object[]{"P012",89,"Mysoor Dhal 500g",90.00},
                    new Object[]{"P013",59,"Mysoor Dhal 1Kg",180.00},
                    new Object[]{"P014",39,"Orient Green Gram 500g",118.00},
                    new Object[]{"P015",12,"Orient Green Gram 1Kg",220.00},
                    new Object[]{"P016",93,"Anchor F/C Milk powder 450g",220.00},
                    new Object[]{"P017",40,"Anchor F/C Milk powder 1Kg",580.00},
                    new Object[]{"P018",33,"Anchor N/F Milk powder 1Kg",560.00},
                    new Object[]{"P019",33,"Milo Packet 400g",240.00},
                    new Object[]{"P020",40,"Lipton Ceylon Tea 100g",107.00}
                )
            );
        } catch (Exception e) {
            log.warn("Insert into items failed. Check table & columns.", e);
        }

        // Quick counts to confirm
        try {
            Integer u = jdbc.queryForObject("SELECT COUNT(*) FROM `user`", Integer.class);
            Integer c = jdbc.queryForObject("SELECT COUNT(*) FROM customer", Integer.class);
            Integer i = jdbc.queryForObject("SELECT COUNT(*) FROM items", Integer.class);
            log.info("Seed complete → users={}, customers={}, items={}", u, c, i);
        } catch (Exception e) {
            log.warn("Count queries failed. Verify schema/permissions.", e);
        }
    }
}
