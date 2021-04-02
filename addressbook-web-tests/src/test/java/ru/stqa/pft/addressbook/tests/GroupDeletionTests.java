

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {


    @Test
    public void testGroupDeletion() throws Exception {

        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {     //создание группы, если ее не было
            app.getGroupHelper().createGroup(new groupData("test", null, null));
        }
        List<groupData> before = app.getGroupHelper().getGroupList(); //подсчет количества групп до создания
        app.getGroupHelper().selectGroup(before.size() - 1); //выбрать последнюю группу
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<groupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1); //удалили группу из списка и проверили
        Assert.assertEquals(before,after);

    }


}
