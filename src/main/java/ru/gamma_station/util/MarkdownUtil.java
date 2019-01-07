package ru.gamma_station.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownUtil {
    public static String translateMarkdownTextToHTML(String markdownText) {
        String result = translateMarkdownBoldCursiveUnderlineToHTML(markdownText);

        result = translateMarkdownBoldUnderlineToHTML(result);
        result = translateMarkdownCursiveUnderlineToHTML(result);
        result = translateMarkdownBoldCursiveToHTML(result);
        result = translateMarkdownBoldToHTML(result);
        result = translateMarkdownCursiveToHTML(result);
        result = translateMarkdownUnderlineToHTML(result);
        result = translateMarkdownStrikeToHTML(result);

        return result;
    }

    private static final Pattern cursive = Pattern.compile("(\\*(.*)\\*)|(_(.*)_)");
    private static String translateMarkdownCursiveToHTML(String markdownText) {
        Matcher matcher = cursive.matcher(markdownText);

        String result = markdownText;
        while (matcher.find()) {
            result = matcher.replaceFirst("<i>" + (matcher.group(2) == null ? matcher.group(4) : matcher.group(2)) + "</i>");
        }

        return result;
    }

    private static final Pattern bold = Pattern.compile("\\*\\*(.*)\\*\\*");
    private static String translateMarkdownBoldToHTML(String markdownText) {
        Matcher matcher = bold.matcher(markdownText);

        String result = markdownText;
        while (matcher.find()) {
            result = matcher.replaceFirst("<b>" + matcher.group(1) + "</b>");
        }

        return result;
    }

    private static final Pattern boldAndCursive = Pattern.compile("\\*\\*\\*(.*)\\*\\*\\*");
    private static String translateMarkdownBoldCursiveToHTML(String markdownText) {
        Matcher matcher = boldAndCursive.matcher(markdownText);

        String result = markdownText;
        while (matcher.find()) {
            result = matcher.replaceFirst("<b><i>" + matcher.group(1) + "</i></b>");
        }

        return result;
    }

    private static final Pattern underline = Pattern.compile("__(.*)__");
    private static String translateMarkdownUnderlineToHTML(String markdownText) {
        Matcher matcher = underline.matcher(markdownText);

        String result = markdownText;
        while (matcher.find()) {
            result = matcher.replaceFirst("<u>" + matcher.group(1) + "</u>");
        }

        return result;
    }

    private static final Pattern strike = Pattern.compile("~~(.*)~~");
    private static String translateMarkdownStrikeToHTML(String markdownText) {
        Matcher matcher = strike.matcher(markdownText);

        String result = markdownText;
        while (matcher.find()) {
            result = matcher.replaceFirst("<s>" + matcher.group(1) + "</s>");
        }

        return result;
    }

    private static final Pattern boldAndUnderline = Pattern.compile("__\\*\\*(.*)\\*\\*__");
    private static String translateMarkdownBoldUnderlineToHTML(String markdownText) {
        Matcher matcher = boldAndUnderline.matcher(markdownText);

        String result = markdownText;
        while (matcher.find()) {
            result = matcher.replaceFirst("<b><u>" + matcher.group(1) + "</u></b>");
        }

        return result;
    }

    private static final Pattern cursiveAndUnderline = Pattern.compile("__\\*(.*)\\*__");
    private static String translateMarkdownCursiveUnderlineToHTML(String markdownText) {
        Matcher matcher = cursiveAndUnderline.matcher(markdownText);

        String result = markdownText;
        while (matcher.find()) {
            result = matcher.replaceFirst("<i><u>" + matcher.group(1) + "</u></i>");
        }

        return result;
    }

    private static final Pattern boldCursiveUnderline = Pattern.compile("__\\*\\*\\*(.*)\\*\\*\\*__");
    private static String translateMarkdownBoldCursiveUnderlineToHTML(String markdownText) {
        Matcher matcher = boldCursiveUnderline.matcher(markdownText);

        String result = markdownText;
        while (matcher.find()) {
            result = matcher.replaceFirst("<b><i>" + matcher.group(1) + "</i></b>");
        }

        return result;
    }
}