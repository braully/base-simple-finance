package com.github.braully.web;

import j2html.TagCreator;
import j2html.tags.ContainerTag;
import j2html.tags.EmptyTag;
import j2html.tags.Tag;

import static j2html.TagCreator.*;

import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * @author braully
 */
public class GeneratorHtmlAngularBootstrap {

    public static final String DEFAULT_TYPE = "div";
    public static final String FORM_TYPE = "form";
    public static final String TABLE_TYPE = "table";
    public static final String INPUT_TYPE = "input";
    public static final String CHECK_TYPE = "input";
    public static final String NG_MODEL = "ng-model";
    public static final String LABEL_CLASS = "col-sm-1";
    public static final String ROW_CLASS = "form-group";

    public String renderForm(DescriptorHtmlEntity html) {
        return renderFormLocal(html, false);
    }

    public String renderFormOnlyChilds(DescriptorHtmlEntity html) {
        return renderFormLocal(html, true);
    }

    private String renderFormLocal(DescriptorHtmlEntity html, boolean onlychilds) {
        String typeRoot = html.type;
        if (typeRoot == null) {
            typeRoot = FORM_TYPE;
        }
        ContainerTag txtHtml = new ContainerTag(getHtmlType(typeRoot));

        if (html.elements != null) {
            for (HtmlElement he : html.elements) {
                ContainerTag parent = txtHtml;

                if (!StringUtils.isEmpty(he.label)) {
                    parent = TagCreator.div();
                    parent.withClass(ROW_CLASS);
                    txtHtml.with(parent);
                    ContainerTag labelHtml = TagCreator.label(he.label);
                    parent.with(labelHtml);
                }

                EmptyTag the = TagCreator.emptyTag(getHtmlType(he.type));
                if (he.attributes != null) {
                    he.attributes.entrySet().stream().forEach((at) -> {
                        the.setAttribute(at.getKey(), at.getValue());
                    });
                }
                the.withClass("form-control");
                the.attr(NG_MODEL, buildNgModelPath(html.property, he.property));

                parent.with(the);
            }
        }
        if (onlychilds) {
            StringBuilder childs = new StringBuilder();
            if (txtHtml.children != null) {
                txtHtml.children.stream().forEach((t) -> {
                    childs.append(t.render());
                });
            }
            return childs.toString();
        } else {
            return txtHtml.render();
        }
    }

    private String getHtmlType(String type) {
        String ret = null;
        if (type != null) {
            switch (type.toLowerCase()) {
                case "string":
                    ret = INPUT_TYPE;
                    break;
                case "boolean":
                    ret = CHECK_TYPE;
                    break;
                case "int":
                case "integer":
                case "long":
                case "float":
                case "double":
                    ret = INPUT_TYPE;
                    break;
                default:
                    ret = type;
                    break;
            }
        }
        return ret;
    }

    private String buildNgModelPath(String... property) {
        return buildNgModelPath(false, property);
    }

    private String buildNgModelPath(boolean brackets, String... property) {
        StringBuilder sb = new StringBuilder();
        if (brackets) {
            sb.append("{{ ");
        }
        if (property != null && property.length > 0) {
            for (int i = 0; i < property.length; i++) {
                String p = property[i];
                sb.append(p);
                if (i == property.length - 2) {
                    sb.append(".");
                }
            }
        }
        if (brackets) {
            sb.append(" }}");
        }
        return sb.toString();
    }

    private String buildNgRepeatPath(String var, String collection) {
        StringBuilder sb = new StringBuilder();
        if (var != null && collection != null) {
            sb.append(var);
            sb.append(" in ");
            sb.append(collection);
        }
        return sb.toString();
    }

    public String renderTableSimple(DescriptorHtmlEntity htmlDescriptor) {
        String typeRoot = htmlDescriptor.type;
        if (typeRoot == null) {
            typeRoot = TABLE_TYPE;
        }
        ContainerTag txtHtml = new ContainerTag(getHtmlType(typeRoot));

        if (htmlDescriptor.elements != null) {
            ContainerTag thead = TagCreator.thead();
            ContainerTag tr = TagCreator.tr();

            for (HtmlElement he : htmlDescriptor.elements) {
                if (!StringUtils.isEmpty(he.label)) {
                    ContainerTag th = TagCreator.th(he.label);
                    tr.with(th);
                }
            }
            thead.with(tr);
            txtHtml.with(thead);

            String var = htmlDescriptor.property + "Item";
            String collection = htmlDescriptor.property + "s";

            ContainerTag tbody = TagCreator.tbody();
            ContainerTag trBody = TagCreator.tr();
            trBody.attr("ng-repeat", buildNgRepeatPath(var, collection));

            for (HtmlElement he : htmlDescriptor.elements) {
                ContainerTag td = TagCreator.td();
                if (he.attributes != null) {
                    he.attributes.entrySet().stream().forEach((at) -> {
                        td.setAttribute(at.getKey(), at.getValue());
                    });
                }
                td.withText(buildNgModelPath(true, var, he.property));
                trBody.with(td);
            }
            tbody.with(trBody);
            txtHtml.with(tbody);
        }
        return txtHtml.render();
    }

    public String renderFilter(DescriptorHtmlEntity htmlDescriptor) {
        String typeRoot = html.type;
        if (typeRoot == null) {
            typeRoot = FORM_TYPE;
        }
        ContainerTag txtHtml = new ContainerTag(getHtmlType(typeRoot));

        if (html.elements != null) {
            for (HtmlElement he : html.elements) {
                ContainerTag parent = txtHtml;

                if (!StringUtils.isEmpty(he.label)) {
                    parent = TagCreator.div();
                    parent.withClass(ROW_CLASS);
                    txtHtml.with(parent);
                    ContainerTag labelHtml = TagCreator.label(he.label);
                    parent.with(labelHtml);
                }

                EmptyTag the = TagCreator.emptyTag(getHtmlType(he.type));
                if (he.attributes != null) {
                    he.attributes.entrySet().stream().forEach((at) -> {
                        the.setAttribute(at.getKey(), at.getValue());
                    });
                }
                the.withClass("form-control");
                the.attr(NG_MODEL, buildNgModelPath(html.property, he.property));

                parent.with(the);
            }
        }

        ContainerTag collapse = div().withClass("collapse").withId("advanced-search");
        collapse.with(button("Advanced").withClass("btn btn-default").withType("button")
                .attr("data-toggle", "collapse").attr("data-target", "#advanced-search").attr("aria-expand", "false")
                .attr("aria-controls", "advanced-search")
                .with(span().withClass("glyphicon glyphicon-option-vertical")));

        txtHtml.with(collapse);

        if (true) {
            StringBuilder childs = new StringBuilder();
            if (txtHtml.children != null) {
                txtHtml.children.stream().forEach((t) -> {
                    childs.append(t.render());
                });
            }
            return childs.toString();
        } else {
            return txtHtml.render();
        }
    }

    public String renderTable(DescriptorHtmlEntity htmlDescriptor) {
        String typeRoot = htmlDescriptor.type;
        if (typeRoot == null) {
            typeRoot = TABLE_TYPE;
        }
        ContainerTag txtHtml = new ContainerTag(getHtmlType(typeRoot));
        txtHtml.withClass("table table-bordred table-striped");

        if (htmlDescriptor.elements != null) {
            ContainerTag thead = TagCreator.thead();
            ContainerTag tr = TagCreator.tr();

            for (HtmlElement he : htmlDescriptor.elements) {
                if (!StringUtils.isEmpty(he.label)) {
                    ContainerTag th = TagCreator.th(he.label);
                    tr.with(th);
                }
            }

            ContainerTag th = TagCreator.th("Edit");
            tr.with(th);
            th = TagCreator.th("Delete");
            tr.with(th);

            thead.with(tr);
            txtHtml.with(thead);

            String var = htmlDescriptor.property + "Item";
            String collection = htmlDescriptor.property + "s";

            ContainerTag tbody = TagCreator.tbody();
            ContainerTag trBody = TagCreator.tr();
            trBody.attr("ng-repeat", buildNgRepeatPath(var, collection));

            for (HtmlElement he : htmlDescriptor.elements) {
                ContainerTag td = TagCreator.td();
                if (he.attributes != null) {
                    he.attributes.entrySet().stream().forEach((at) -> {
                        td.setAttribute(at.getKey(), at.getValue());
                    });
                }
                td.withText(buildNgModelPath(true, var, he.property));
                trBody.with(td);
            }

            ContainerTag td = TagCreator.td();
            td.with(p().attr("data-placement", "top").attr("data-toggle", "tooltip").attr("title", "Edit").with(
                    button().withClass("btn btn-primary btn-xs").attr("data-title", "Edit")
                            .attr("data-toggle", "modal").attr("data-target", "#edit").with(
                            span().withClass("glyphicon glyphicon-pencil"))));
            trBody.with(td);

            td = TagCreator.td();
            td.with(p().attr("data-placement", "top").attr("data-toggle", "tooltip").attr("title", "Delete").with(
                    button().withClass("btn btn-danger btn-xs").attr("data-title", "Delete")
                            .attr("data-toggle", "modal").attr("data-target", "#delete").with(
                            span().withClass("glyphicon glyphicon-trash"))));
            trBody.with(td);

            tbody.with(trBody);
            txtHtml.with(tbody);
        }
        return txtHtml.render();
        return ret;
    }
}
