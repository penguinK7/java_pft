package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.Comparator;
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
        groupData group = new groupData(before.get(before.size()-1).getId(),"test", "test1", "test2");
        app.getGroupHelper().fillGroupForm(group );
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<groupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() );

        before.remove(before.size() - 1);
        before.add(group);
        Comparator<? super groupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
