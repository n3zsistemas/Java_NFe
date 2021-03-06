package br.com.samuelweb.nfe.util.model;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjeto;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCNPJCPF;
import br.com.samuelweb.nfe.util.validators.impl.ValidarCRT;
import br.com.samuelweb.nfe.util.validators.impl.ValidarIE;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

public class Emit {

    @NfeCampo(tipo = String.class
            , id = "C02", tag = "CNPJ", validadores = {ValidarCNPJCPF.class}
            , tamanhoMinimo = 11, tamanhoMaximo = 14, ocorrencias = 1
            , descricao = DfeConsts.DSC_CNPJ)
    private String cnpjCpf;

    @NfeCampo(tipo = String.class
            , id = "C03", tag = "xNome"
            , tamanhoMinimo = 2, tamanhoMaximo = 60, ocorrencias = 1
            , descricao = DfeConsts.DSC_XNOME)
    private String xNome;

    @NfeCampo(tipo = String.class
            , id = "C04", tag = "xFant"
            , tamanhoMinimo = 1, tamanhoMaximo = 60, ocorrencias = 0
            , descricao = DfeConsts.DSC_XFANT)
    private String xFant;

    @NfeObjeto(id = "C05", tag = "enderEmit"
            , ocorrencias = 1, descricao = NfeConsts.DSC_ENDEREMIT)
    private EnderEmi enderEmit;

    @NfeCampo(tipo = String.class
            , id = "C17", tag = "IE", validadores = {ValidarIE.class}
            , tamanhoMinimo = 0, tamanhoMaximo = 14, ocorrencias = 1
            , descricao = DfeConsts.DSC_IE)
    private String ie;

    @NfeCampo(tipo = String.class
            , id = "C18", tag = "IEST"
            , tamanhoMinimo = 2, tamanhoMaximo = 14, ocorrencias = 0
            , descricao = DfeConsts.DSC_IEST)
    private String iest;

    @NfeCampo(tipo = String.class
            , id = "C19", tag = "IM"
            , tamanhoMinimo = 1, tamanhoMaximo = 15, ocorrencias = 0
            , descricao = DfeConsts.DSC_IM)
    private String im;

    @NfeCampo(tipo = String.class
            , id = "C20", tag = "CNAE"
            , tamanhoMinimo = 7, tamanhoMaximo = 7, ocorrencias = 0
            , descricao = DfeConsts.DSC_CNAE)
    private String cnae;

    @NfeCampo(tipo = String.class
            , id = "C21", tag = "CRT", validadores = {ValidarCRT.class}
            , tamanhoMinimo = 1, tamanhoMaximo = 1, ocorrencias = 1
            , descricao = NfeConsts.DSC_CRT)
    private String crt;

    public Emit() {
        this.enderEmit = new EnderEmi();
    }

    public TNFe.InfNFe.Emit build() {
        TNFe.InfNFe.Emit emit = new TNFe.InfNFe.Emit();
        if (getCnpjCpf().length() > 11) {
            emit.setCNPJ(getCnpjCpf());
        } else {
            emit.setCPF(getCnpjCpf());
        }
        emit.setXNome(this.getxNome());
        emit.setXFant(this.getxFant());
        emit.setEnderEmit(this.getEnderEmit().build());
        if (this.getIe() != null && !this.getIe().equals("ISENTO")) {
            this.setIe(this.getIe().replaceAll("\\D", ""));
        }
        emit.setIE(this.getIe());
        if (this.getIest() != null && !this.getIest().isEmpty()) {
            emit.setIEST(this.getIest());
        }
        if (this.getIm() != null && !this.getIm().isEmpty()) {
            emit.setIM(this.getIm());
        }
        // NT 2013/005 versão 1.03
        // o CNAE passa ser opcional mesmo quando informado o IM, mas o CNAE s� pode
        // ser informado se o IM for informado.
        if (this.getIm() != null && !this.getIm().isEmpty()) {
            emit.setCNAE(this.getCnae());
        }
        emit.setCRT(this.getCrt());
        return emit;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public String getxNome() {
        return xNome;
    }

    public String getxFant() {
        return xFant;
    }

    public EnderEmi getEnderEmit() {
        return enderEmit;
    }

    public String getIe() {
        return ie;
    }

    public String getIest() {
        return iest;
    }

    public String getIm() {
        return im;
    }

    public String getCnae() {
        return cnae;
    }

    public String getCrt() {
        return crt;
    }

    public Emit setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
        return this;
    }

    public Emit setxNome(String xNome) {
        this.xNome = xNome;
        return this;
    }

    public Emit setxFant(String xFant) {
        this.xFant = xFant;
        return this;
    }

    public Emit setEnderEmit(EnderEmi enderEmit) {
        this.enderEmit = enderEmit;
        return this;
    }

    public Emit setIe(String ie) {
        this.ie = ie;
        return this;
    }

    public Emit setIest(String iest) {
        this.iest = iest;
        return this;
    }

    public Emit setIm(String im) {
        this.im = im;
        return this;
    }

    public Emit setCnae(String cnae) {
        this.cnae = cnae;
        return this;
    }

    public Emit setCrt(String crt) {
        this.crt = crt;
        return this;
    }

    public void validarRegraNegocio(InfNFe infNFe) {
        this.getEnderEmit().validarRegraNegocio(infNFe);
    }
}
