package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.groupData;
import java.util.List;
public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

        app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount(); //подсчет количества групп до создания
    app.getGroupHelper().createGroup(new groupData("test", null, null));
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before + 1);

  }


}
