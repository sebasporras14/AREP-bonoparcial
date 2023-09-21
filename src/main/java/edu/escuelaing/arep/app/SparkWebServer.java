package edu.escuelaing.arep.app;

import static spark.Spark.*;

public class SparkWebServer {
    
    public static void main(String... args) {
        port(getPort());
    
        get("hello", (req, res) -> "Hello Docker!");

        get("/", (req, res) -> getIndexResponse());

        get("/sin/:number", (req, res) -> {
            double number = Double.parseDouble(req.params("number"));
            return Math.sin(number);
        });
        get("/cos/:number", (req, res) -> {
            double number = Double.parseDouble(req.params("number"));
            return Math.cos(number);
        });
        get("/palindromo/:cadena", (req, res) -> {
            String cadena = req.params("cadena");
            if(Palindrome(cadena)){
                return "Es un palíndromo";
            }
            return "No es un palíndromo";
        });
        get("/magnitud/:x/:y", (req, res) -> {
            double x = Double.parseDouble(req.params("x"));
            double y = Double.parseDouble(req.params("y"));
            return magnitude(x, y);
        });
    }


    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static boolean Palindrome(String string) {
        boolean palindrome = true;
        for (int i = 0; i < string.length() / 2; i++) {
            if (string.charAt(i) != string.charAt(string.length() - i - 1)) {
                palindrome = false;
                break;
            }
        }
        return palindrome;
    }

    private static Double magnitude(Double x, Double y){
        Double magnitud = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return magnitud;
    }

    public static String getIndexResponse() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <style>\n" +
                "body{\n" +
                "    align-content: center;\n" +
                "    text-align: center\n;" +
                "    justify-content: center\n;" +
                "}\n" +
                "    </style>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>SparkWebApp</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>BONO PARCIAL AREP</h1><br><br>\n" +
                "<form id=\"sinForm\">\n" +
                "    <label for=\"sinInput\">Calcular Seno(): </label>\n" +
                "    <input type=\"number\" id=\"sinInput\" step=\"any\" required>\n" +
                "    <button type=\"button\" onclick=\"calculateSin()\">Calcular</button>\n" +
                "    <div id=\"sinResult\"></div>\n" +
                "</form><br><br>\n" +
                "\n" +
                "<form id=\"cosForm\">\n" +
                "    <label for=\"cosInput\">Calcular Cos: </label>\n" +
                "    <input type=\"number\" id=\"cosInput\" step=\"any\" required>\n" +
                "    <button type=\"button\" onclick=\"calculateCos()\">Calcular</button>\n" +
                "    <div id=\"cosResult\"></div>\n" +
                "</form><br><br>\n" +
                "\n" +
                "<form id=\"palindromeForm\">\n" +
                "    <label for=\"palindromeInput\">Verificar Palíndromo: </label>\n" +
                "    <input type=\"text\" id=\"palindromeInput\" required>\n" +
                "    <button type=\"button\" onclick=\"checkPalindrome()\">Verificar</button>\n" +
                "    <div id=\"palindromeResult\"></div>\n" +
                "</form><br><br>\n" +
                "\n" +
                "<form id=\"magnitudeForm\">\n" +
                "    <label for=\"xInput\">Valor X: </label>\n" +
                "    <input type=\"number\" id=\"xInput\" step=\"any\" required>\n" +
                "    <label for=\"yInput\">Valor Y: </label>\n" +
                "    <input type=\"number\" id=\"yInput\" step=\"any\" required>\n" +
                "    <button type=\"button\" onclick=\"calculateMagnitude()\">Calcular Magnitud</button>\n" +
                "    <div id=\"magnitudeResult\"></div>\n" +
                "</form><br><br>\n" +
                "\n" +
                "<script>\n" +
                "    function calculateSin() {\n" +
                "        const input = document.getElementById(\"sinInput\").value;\n" +
                "        fetch(`/sin/${input}`)\n" +
                "            .then(response => response.text())\n" +
                "            .then(result => {\n" +
                "                document.getElementById(\"sinResult\").textContent = `Sin(${input}) = ${result}`;\n" +
                "            });\n" +
                "    }\n" +
                "\n" +
                "    function calculateCos() {\n" +
                "        const input = document.getElementById(\"cosInput\").value;\n" +
                "        fetch(`/cos/${input}`)\n" +
                "            .then(response => response.text())\n" +
                "            .then(result => {\n" +
                "                document.getElementById(\"cosResult\").textContent = `Cos(${input}) = ${result}`;\n" +
                "            });\n" +
                "    }\n" +
                "\n" +
                "    function checkPalindrome() {\n" +
                "        const input = document.getElementById(\"palindromeInput\").value;\n" +
                "        fetch(`/palindromo/${input}`)\n" +
                "            .then(response => response.text())\n" +
                "            .then(result => {\n" +
                "                document.getElementById(\"palindromeResult\").textContent = result;\n" +
                "            });\n" +
                "    }\n" +
                "\n" +
                "    function calculateMagnitude() {\n" +
                "        const x = document.getElementById(\"xInput\").value;\n" +
                "        const y = document.getElementById(\"yInput\").value;\n" +
                "        fetch(`/magnitud/${x}/${y}`)\n" +
                "            .then(response => response.text())\n" +
                "            .then(result => {\n" +
                "                document.getElementById(\"magnitudeResult\").textContent = `Magnitud: ${result}`;\n" +
                "            });\n" +
                "    }\n" +
                "            document.getElementById(\"sinInput\").addEventListener(\"keydown\", function(event) {\n" +
                "                if (event.key === \"Enter\") {\n" +
                "                    event.preventDefault();\n" +
                "                    calculateSin();\n" +
                "                }\n" +
                "            });\n" +
                "            document.getElementById(\"cosInput\").addEventListener(\"keydown\", function(event) {\n" +
                "                if (event.key === \"Enter\") {\n" +
                "                    event.preventDefault();\n" +
                "                    calculateCos();\n" +
                "                }\n" +
                "            });\n" +
                "            document.getElementById(\"palindromeInput\").addEventListener(\"keydown\", function(event) {\n" +
                "                if (event.key === \"Enter\") {\n" +
                "                    event.preventDefault();\n" +
                "                    checkPalindrome();\n" +
                "                }\n" +
                "            });\n" +
                "            document.getElementById(\"xInput\").addEventListener(\"keydown\", function(event) {\n" +
                "                if (event.key === \"Enter\") {\n" +
                "                    event.preventDefault();\n" +
                "                    calculateMagnitude();\n" +
                "                }\n" +
                "            });\n" +
                "            document.getElementById(\"yInput\").addEventListener(\"keydown\", function(event) {\n" +
                "                if (event.key === \"Enter\") {\n" +
                "                    event.preventDefault();\n" +
                "                    calculateMagnitude();\n" +
                "                }\n" +
                "            });\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>\n";
    }

    
}
