package orangetaxiteam.cocoman.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class PartyService {
    private final PartyRepository partyRepository;

    @Autowired
    public PartyService(PartyRepository partyRepository){
        this.partyRepository = partyRepository;
    }

    // TODO : change to InputValueValidationException
    private void validateDuplicatePartyName(Party party) {
        partyRepository.findByPartyName(party.getPartyName())
                .ifPresent(m->{
                    throw  new IllegalStateException("이미 존재하는 파티 이름");
                });
    }

    public List<Party> findAll() {
        return partyRepository.findAll();
    }

    public Party create(Long ownerId, String partyName, String ott, Double price,
                        Date payDay, Integer maxMember, Date startDate, PartyStatus status) {
        return partyRepository.save(new Party(ownerId, partyName, ott, price, payDay, maxMember, startDate, status));
    }

}