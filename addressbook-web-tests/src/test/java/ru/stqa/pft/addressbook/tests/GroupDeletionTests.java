

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.group().list().size() == 0){  //создание группы, если ее не было
            app.group().create(new groupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {


        List<groupData> before = app.group().list(); //подсчет количества групп до создания
        int index = before.size()-1;
        app.group().delete(index);
        List<groupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(index); //удалили группу из списка и проверили
        Assert.assertEquals(before,after);

    }




}
