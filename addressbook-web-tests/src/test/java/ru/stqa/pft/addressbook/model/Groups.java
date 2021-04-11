package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<groupData> {
    private Set<groupData> delegate;

    public Groups(Groups groups) {
        this.delegate = new HashSet<groupData>(groups.delegate);
    }

    public Groups() {
        this.delegate = new HashSet<>();
    }

    @Override
    protected Set<groupData> delegate() {
        return delegate;
    }

    public Groups withAdded(groupData group){
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }
    public Groups without(groupData group){
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }
}
