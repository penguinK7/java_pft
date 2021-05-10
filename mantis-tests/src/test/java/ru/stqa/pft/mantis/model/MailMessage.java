package ru.stqa.pft.mantis.model;

public class MailMessage { // сделали свой модельный объект типа Message

    public String to;    // кому пришло письмо
    public String text;    // текст письма

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
} 