package imerssao.java.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParse {
    private static final Pattern REGEX_ITENS = Pattern.compile(".*\\[(.+)\\].*"); //expressões regulares
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
    public List<Map<String, String>> parse(String json){
        Matcher matcher = REGEX_ITENS.matcher(json);
        if (!matcher.find()){
            throw new IllegalArgumentException("Não encontrou itens.");
        }
        String[] items = matcher.group(1).split("\\},\\{");
        List<Map<String, String>> dadosColetatos = new ArrayList<>();
        for (String mostrar: items) {
            Map<String, String> atributosItem = new HashMap<>();

            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(mostrar);
            while (matcherAtributosJson.find()){
                String atributo =  matcherAtributosJson.group(1);
                String  valor = matcherAtributosJson.group(2);
                atributosItem.put(atributo, valor);
            }
        }
    }
}
