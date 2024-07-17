package com.resendegabriel.investmentscontrolapi.controller.docs.wallet;

import com.resendegabriel.investmentscontrolapi.exception.StandardError;
import com.resendegabriel.investmentscontrolapi.model.dto.wallet.WalletResponse;
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
        summary = "Busca todas carteiras de investimentos de um usuario",
        description = "Busca todas as carteiras de investimento por meio do id do usuario")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Retorna uma lista com todas as carteiras de invetimento de um usuario",
                content = @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = WalletResponse.class)))),
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
public @interface GetWalletsByUserIdDoc {
}
