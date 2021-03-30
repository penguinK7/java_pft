

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.groupData;

public class GroupDeletionTests extends TestBase{


  @Test
  public void testGroupDeletion() throws Exception {

    app.getNavigationHelper().gotoGroupPage();
    if(! app.getGroupHelper().isThereAGroup()){     //создание группы, если ее не было
     app.getGroupHelper().createGroup(new groupData("test", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();

  }


}
