package com.resendegabriel.investmentscontrolapi.controller.docs.dividend;

import com.resendegabriel.investmentscontrolapi.exception.StandardError;
import com.resendegabriel.investmentscontrolapi.model.dto.divedend.DividendResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Adiciona um novo dividendo a um ativo",
        description = "Esse endpoint serve para adicionar um novo dividendo a um ativo de renda variavel")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "201",
                description = "Dividendo criado com sucesso.",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = DividendResponse.class))),
        @ApiResponse(
                responseCode = "400",
                description = "Requisição Inválida",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(
                responseCode = "403",
                description = "Acesso nao autorizado",
                content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(
                responseCode = "404",
                description = "Recurso não encontrado",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(
                responseCode = "500",
                description = "Erro no servidor",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = StandardError.class)))
})
public @interface SaveDividendDoc {
}
