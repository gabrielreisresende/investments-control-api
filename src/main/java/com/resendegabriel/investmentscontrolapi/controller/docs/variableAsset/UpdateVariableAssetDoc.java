package com.resendegabriel.investmentscontrolapi.controller.docs.variableAsset;

import com.resendegabriel.investmentscontrolapi.model.dto.StockResponse;
import com.resendegabriel.investmentscontrolapi.exception.StandardError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
        summary = "Atualiza um ativo de renda variável",
        description = "Esse endpoint serve para atualizar a quantidade e o valor médio de um ativo de renda variável existente na carteira de investimentos")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Ativo atualizado com sucesso.",
                content = @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = StockResponse.class)))),
        @ApiResponse(
                responseCode = "400",
                description = "Requisição Inválida",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = StandardError.class))),
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
public @interface UpdateVariableAssetDoc {
}
