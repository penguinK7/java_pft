package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() throws Exception {

        app.goTo().groupPage();
        List<groupData> before = app.group().list(); //подсчет количества групп до создания
        groupData group = new groupData("test", null, null);
        app.group().create(group);
        List<groupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);


      //  group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());//сравниваем по ид
        before.add(group);
        Comparator<? super groupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
