package gemini.FitGymGpt.Service.User;

import gemini.FitGymGpt.DataBase.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

        private final UserRepository userRepository;

        @Override
    public UserDetails loadUserByUsername(String username) {
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException(username + " Não foi encontrado!"));
        }
}
