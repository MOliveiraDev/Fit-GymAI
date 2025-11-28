package gemini.FitGymGpt.strategy.fitcenter.impl;

import gemini.FitGymGpt.database.repository.fitcenter.GymCenterEntityRepository;
import gemini.FitGymGpt.dto.fitcenter.CreateGymCenterRequest;
import gemini.FitGymGpt.strategy.fitcenter.IGymCenterValidations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaxIdActuallyExists implements IGymCenterValidations {

    private final GymCenterEntityRepository gymCenterEntityRepository;

    @Override
    @SneakyThrows
    public void gymCenterValidations(@Valid CreateGymCenterRequest request){
        if (gymCenterEntityRepository.findByTaxId(request.taxId())) {
            throw new IllegalArgumentException("O CNPJ informado já está cadastrado no sistema");
        }
    }
}
