package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.groupData;

public class GroupModificationTests extends TestBase{
    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupHelper().isThereAGroup()){  //создание группы, если ее не было
            app.getGroupHelper().createGroup(new groupData("test", null, null));
        }
        int before = app.getGroupHelper().getGroupCount(); //подсчет количества групп до создания
        app.getGroupHelper().selectGroup(before-1); //выбрать последнюю группу
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new groupData("test", "test1", "test2"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before );

    }
}
