package com.krythera.classloadtest;

import java.util.ServiceLoader;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.exposed.sql.Database;
import org.jetbrains.exposed.sql.DatabaseConnectionAutoRegistration;
import org.jetbrains.exposed.sql.transactions.ThreadLocalTransactionManager;

@Mod(ClassloadTest.MOD_ID)
public class ClassloadTest {

  public static final String MOD_ID = "kryclassloadtest";

  private static final Logger LOGGER = LogManager.getLogger(ClassloadTest.class);

  public ClassloadTest() {
    ServiceLoader<DatabaseConnectionAutoRegistration> loader = ServiceLoader
        .load(DatabaseConnectionAutoRegistration.class, Database.class.getClassLoader());

    LOGGER.debug(
        "loader found value: " + loader.iterator().hasNext());

    Database.Companion.connect("jdbc:h2:./test", "org.h2.Driver", "", "", (c) -> null,
        (db) -> new ThreadLocalTransactionManager(db, 3));
  }
}
