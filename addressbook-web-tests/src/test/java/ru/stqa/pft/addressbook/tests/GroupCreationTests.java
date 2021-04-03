package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() throws Exception {

        app.getNavigationHelper().gotoGroupPage();
        List<groupData> before = app.getGroupHelper().getGroupList(); //подсчет количества групп до создания
        groupData group = new groupData("test", null, null);
        app.getGroupHelper().createGroup(group);
        List<groupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);


     /*  int max = 0;
        for (groupData g : after){
            if (g.getId() > max){
                max = g.getId();
            }
        }*/
      //  Comparator<? super groupData> byId = (Comparator<groupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
      //  int max = after.stream().max( (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId(); //сравниваем по ид
        group.setId(after.stream().max( (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    }


}
