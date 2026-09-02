package com.stockmaster.stockmaster;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

@RestController
public class Estoque {
    private List<Produto> listaDeProdutos;
    private final String NOME_ARQUIVO = "estoque.txt"; 

    public Estoque() {
        this.listaDeProdutos = new ArrayList<>();
        carregarDados(); 
    }

    @GetMapping("/produtos")
    public List<Produto> getProdutosWeb() {
        return listaDeProdutos;
    }

    @PostMapping("/produtos")
    public Produto cadastrarProdutoWeb(@RequestBody Produto novoProduto) {
        adicionarProduto(novoProduto); 
        salvarDados(); 
        return novoProduto;
    }

    @DeleteMapping("/produtos/{id}")
    public void excluirProdutoWeb(@PathVariable int id) {
        if (removerProduto(id)) {
            salvarDados(); 
        }
    }

    // NOVA ROTA: Registrar Entrada
    @PutMapping("/produtos/{id}/entrada")
    public void registrarEntradaWeb(@PathVariable int id, @RequestParam int qtd, @RequestParam double peso) {
        Produto p = buscarPorId(id);
        if (p != null) {
            p.registrarEntrada(qtd, peso);
            salvarDados();
        }
    }

    // NOVA ROTA: Registrar Saída
    @PutMapping("/produtos/{id}/saida")
    public void registrarSaidaWeb(@PathVariable int id, @RequestParam int qtd, @RequestParam double peso) {
        Produto p = buscarPorId(id);
        if (p != null) {
            p.registrarSaida(qtd, peso);
            salvarDados();
        }
    }

    // --- MÉTODOS DE LÓGICA E PERSISTÊNCIA ---
    public void adicionarProduto(Produto produto) {
        listaDeProdutos.add(produto);
    }

    public Produto buscarPorId(int id) {
        for (Produto p : listaDeProdutos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean removerProduto(int id) {
        Produto p = buscarPorId(id);
        if (p != null) {
            listaDeProdutos.remove(p);
            return true;
        }
        return false;
    }

    public void salvarDados() {
        try {
            FileWriter arquivo = new FileWriter(NOME_ARQUIVO);
            PrintWriter gravador = new PrintWriter(arquivo);
            for (Produto p : listaDeProdutos) {
                gravador.println(p.getId() + ";" + p.getDescricao() + ";" + 
                                 p.getQuantidade() + ";" + p.getPeso() + ";" + p.getDataValidade());
            }
            gravador.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    private void carregarDados() {
        try {
            File arquivo = new File(NOME_ARQUIVO);
            if (!arquivo.exists()) return;

            Scanner leitor = new Scanner(arquivo);
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");
                
                int id = Integer.parseInt(dados[0]);
                String desc = dados[1];
                int qtd = Integer.parseInt(dados[2]);
                double peso = Double.parseDouble(dados[3]);
                LocalDate validade = LocalDate.parse(dados[4]);
                
                Produto p = new Produto(id, desc, qtd, peso, validade);
                listaDeProdutos.add(p);
            }
            leitor.close();
            System.out.println("[Web] Banco de dados carregado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao ler: " + e.getMessage());
        }
    }
}