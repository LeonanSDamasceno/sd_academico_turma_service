package br.edu.ifgoiano.academico.sd_academico_turma_service.service;

import br.edu.ifgoiano.grpc.*;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TurmaGrpcServiceImpl
                extends TurmaGrpcServiceGrpc.TurmaGrpcServiceImplBase {

        private final TurmaService turmaService;

        public TurmaGrpcServiceImpl(
                        TurmaService turmaService) {

                this.turmaService = turmaService;
        }

        @Override
        public void reservarVaga(
                        ReservaVagaRequest request,
                        StreamObserver<ReservaVagaResponse> responseObserver) {

                boolean sucesso = turmaService.reservarVaga(
                                request.getTurmaId());

                ReservaVagaResponse response = ReservaVagaResponse.newBuilder()
                                .setSucesso(sucesso)
                                .setMensagem(
                                                sucesso
                                                                ? "Vaga reservada"
                                                                : "Sem vagas disponíveis")
                                .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
        }

        @Override
        public void liberarVaga(
                        LiberaVagaRequest request,
                        StreamObserver<LiberaVagaResponse> responseObserver) {

                boolean sucesso = turmaService.liberarVaga(
                                request.getTurmaId());

                LiberaVagaResponse response = LiberaVagaResponse.newBuilder()
                                .setSucesso(sucesso)
                                .setMensagem(
                                                sucesso
                                                                ? "Vaga liberada"
                                                                : "Erro ao liberar vaga")
                                .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
        }
}
