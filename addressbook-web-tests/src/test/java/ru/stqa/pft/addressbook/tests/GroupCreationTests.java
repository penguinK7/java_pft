package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
    @DataProvider
//заполнение данных для создания группы из файла xml
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
       try( BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))){
           String xml = "";
           String line = reader.readLine();
           while (line != null) {
               xml += line;
               line = reader.readLine();
           }
           XStream xstream = new XStream();
           xstream.processAnnotations(GroupData.class);
           List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
           return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
       }

    }
    @DataProvider
//заполнение данных для создания группы из файла json
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
       try( BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))){
           String json = "";
           String line = reader.readLine();
           while (line != null) {
               json += line;
               line = reader.readLine();
           }
           Gson gson = new Gson();
           List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
           return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
       }

    }



    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData group) throws Exception {


        app.goTo().groupPage();
        Groups before = app.db().groups(); //подсчет количества групп до создания
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.db().groups();


        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));


    }

    @Test(enabled = false)
    public void testBadGroupCreation() throws Exception {

        app.goTo().groupPage();
        Groups before = app.group().all(); //подсчет количества групп до создания
        GroupData group = new GroupData().withName("test1'");//создаем тест с недопустимым символом '
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()));

        assertThat(after, equalTo(before));

        verifyGroupListInUI();
        //-DverifyUI=true   написать в edit configurations  vm options
    }


}
