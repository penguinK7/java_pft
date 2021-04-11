package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.group().all().size() == 0){  //создание группы, если ее не было
            app.group().create(new groupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification(){

        Groups before = app.group().all(); //подсчет количества групп до создания
               groupData modifiedGroup = before.iterator().next();
        groupData group = new groupData()
                .withId(modifiedGroup.getId()).withName("test").withHeader("test1").withFooter("test2");
        app.group().modify(group);
        Groups after = app.group().all();
        assertEquals(after.size(), before.size() );

        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

    }


}
