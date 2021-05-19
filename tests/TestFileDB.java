package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import src.database.*;
import src.database.filedb.FileDB;
import src.database.types.Staff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TestFileDB {
    @Test
    void testFilterBySkill() throws FileNotFoundException {
        String path = new File("./tests/file.txt").getAbsolutePath();
        Database fdb = new FileDB(path);

        ArrayList<String> testcase = new ArrayList<String>();
        testcase.add("programming");
        testcase.add("databaseDesign");

        TableFindable<Staff> staff = fdb.getStaffTable();
        ArrayList<Staff> res = staff.findWithSkills(testcase);
        ArrayList<Staff> expectedResult = new ArrayList<Staff>();
        expectedResult.add(staff.find(3));

        for (int i = 0, length = res.size(); i < length; i++) {
            assertTrue(res.get(i).equals(expectedResult.get(i)));
        }
    }
}
