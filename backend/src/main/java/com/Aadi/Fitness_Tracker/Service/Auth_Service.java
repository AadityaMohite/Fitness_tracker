package com.Aadi.Fitness_Tracker.Service;

import com.Aadi.Fitness_Tracker.Dto.AuthResponse;
import com.Aadi.Fitness_Tracker.Dto.LoginRequest;
import com.Aadi.Fitness_Tracker.Dto.RegisterRequest;

public interface Auth_Service {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
