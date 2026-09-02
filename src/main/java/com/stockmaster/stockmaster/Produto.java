package com.stockmaster.stockmaster;
import java.time.LocalDate;

public class Produto {
    private int id;
    private String descricao;
    private int quantidade;
    private double peso; // Agora tratado como o Peso Total em estoque
    private LocalDate dataValidade;

    public Produto(int id, String descricao, int quantidade, double peso, LocalDate dataValidade) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.peso = peso;
        this.dataValidade = dataValidade;
    }

    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public int getQuantidade() { return quantidade; }
    public double getPeso() { return peso; }
    public LocalDate getDataValidade() { return dataValidade; }

    // Atualiza a QUANTIDADE e o PESO somando ao que já existe
    public void registrarEntrada(int qtd, double pesoAdicional) {
        if (qtd >= 0 && pesoAdicional >= 0) {
            this.quantidade += qtd;
            this.peso += pesoAdicional;
        }
    }

    // Subtrai a QUANTIDADE e o PESO se houver saldo suficiente
    public boolean registrarSaida(int qtd, double pesoSaida) {
        if (qtd >= 0 && pesoSaida >= 0 && this.quantidade >= qtd && this.peso >= pesoSaida) {
            this.quantidade -= qtd;
            this.peso -= pesoSaida;
            return true;
        }
        return false; // Retorna falso se tentar tirar mais kg ou unidades do que tem
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Item: %s | Qtd: %d | Peso Total: %.2fkg | Vence em: %s", 
                              id, descricao, quantidade, peso, dataValidade);
    }
}