

package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.groupData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {  //создание группы, если ее не было
            app.group().create(new groupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {


        Groups before = app.group().all(); //подсчет количества групп до создания
        groupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.group().all();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedGroup)));


    }


}
