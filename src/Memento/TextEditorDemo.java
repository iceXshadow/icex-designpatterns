package Memento;

import java.util.ArrayList;
import java.util.List;

// memento
class TextEditorMemento {
    private final String text;

    public TextEditorMemento(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

// Originator
class TextEditor {
    private String text = "";
    public void type(String newText) {
        text += newText;
    }

    public String getText() {
        return text;
    }

    public TextEditorMemento save() {
        return new TextEditorMemento(text);
    }

    public void restore(TextEditorMemento memento) {
        text = memento.getText();
    }
}

// caretaker
class TextEditorHistory {
    private final List<TextEditorMemento> history = new ArrayList<>();

    public void save(TextEditorMemento memento) {
        history.add(memento);
    }

    public TextEditorMemento undo() {
        if (!history.isEmpty()) {
            TextEditorMemento lastSaved = history.remove(history.size() - 1);
            return lastSaved;
        } else {
            return null;
        }
    }
}

public class TextEditorDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        TextEditorHistory history = new TextEditorHistory();

        editor.type("Hello, ");
        history.save(editor.save());

        editor.type("world!");
        history.save(editor.save());

        System.out.println("Current Text: " + editor.getText());

        editor.restore(history.undo());
        System.out.println("After Undo: " + editor.getText());

        editor.restore(history.undo());
        System.out.println("After Undo: " + editor.getText());
    }
}
