package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.List;

public class GroupModificationTests extends TestBase{
    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupHelper().isThereAGroup()){  //создание группы, если ее не было
            app.getGroupHelper().createGroup(new groupData("test", null, null));
        }
        List<groupData> before = app.getGroupHelper().getGroupList(); //подсчет количества групп до создания
        app.getGroupHelper().selectGroup(before.size()-1); //выбрать последнюю группу
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new groupData("test", "test1", "test2"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<groupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() );

    }
}
