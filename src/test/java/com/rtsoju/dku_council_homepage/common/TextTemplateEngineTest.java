package com.rtsoju.dku_council_homepage.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TextTemplateEngineTest {

    @Test
    void parseHtmlTemplate() {
        String text = new TextTemplateEngine.Builder()
                .argument("title", "테스트 제목")
                .argument("content", "여기에 컨텐츠")
                .build()
                .readHtmlFromResource("test_template_content.html");
        assertThat(text.trim()).isEqualTo("<html>\n" +
                "<head>\n" +
                "    <title>테스트 제목</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <p style=\"font-size: 24pt\">여기에 컨텐츠</p>\n" +
                "</body>\n" +
                "</html>".trim());
    }
}