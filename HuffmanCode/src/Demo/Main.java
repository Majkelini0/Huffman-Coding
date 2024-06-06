package Demo;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = "";
        while (true) {
            System.out.print("Podaj tekst do zakodowania: ");
            input = sc.nextLine();

            if (input.equalsIgnoreCase("q")) {
                break;
            }

            String output = huffman(input);
            System.out.println(output);
        }
    }

    public static String huffman(String input) {
        // input np -> Krol Karol kupil krolowej Karolinie korale koloru koralowego

        Map<Character, Integer> map = new TreeMap<>(); // seg alfa
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1); // zliczanie wystapien
        }
        // print map
//        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
//            System.out.println("Character: '" + entry.getKey() + "' Count: " + entry.getValue());
//        }

        // kolejka priorytetowa -> zmienic na wlasna implementacje
        PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparingInt(Pair::getWaga)); // sortuje po wadze

        for (Map.Entry<Character, Integer> el : map.entrySet()) {
            queue.add(new Pair(el.getKey(), el.getValue()));
        }
        for (Pair p : queue) {
            System.out.println("Character: '" + p.c + "' Count: " + p.waga);
        }

        while (queue.size() > 1) {
            Pair leftEl = queue.poll();
            Pair rightEl = queue.poll();
            queue.add(new Pair(leftEl.waga + rightEl.waga, leftEl, rightEl));
        }

        Pair root = queue.poll(); // korzen

        Map<Character, String> codes = new TreeMap<>(); // mapa kodow

        generateCodes(root, "", codes); // kody

        //
        boolean areHuffman = areHuffmanCodes(codes);
        System.out.println("Is Huffman code ? " + areHuffman);
        //

        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println("Character: '" + entry.getKey() + "' Code: " + entry.getValue());
        }

        StringBuilder encodedString = new StringBuilder(); //zlaczam szukajac kodow po kluczu

        for (char c : input.toCharArray()) {
            String huffmanCode = codes.get(c);
            encodedString.append(huffmanCode);
        }
        return encodedString.toString();

    }

    public static void generateCodes(Pair Pair, String code, Map<Character, String> codes) {
        if (Pair != null) {
            if (Pair.leftEl == null && Pair.rightEl == null) {
                codes.put(Pair.c, code);
            } // jesli jest lisciem to dodajemy kod

            generateCodes(Pair.leftEl, code + '0', codes);
            generateCodes(Pair.rightEl, code + '1', codes);
        }
    }

    // ensure that code is huffman code
    public static boolean areHuffmanCodes(Map<Character, String> codes) {
        Set<String> codeSet = new HashSet<>(codes.values());

        for (String code : codes.values()) {
            for (int i = 0; i < code.length() - 1; i++) {
                String prefix = code.substring(0, i + 1);
                if (codeSet.contains(prefix)) {
                    return false;
                }
            }
        }

        return true;
    }
}
