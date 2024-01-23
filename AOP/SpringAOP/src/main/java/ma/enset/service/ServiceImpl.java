package ma.enset.service;

import ma.enset.aspects.Log;
import ma.enset.aspects.SecuredByAspect;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements IService {
    @Override
    @Log
    @SecuredByAspect(roles = {"ADMIN", "USER"})
    public void process() {
        System.out.println("Business Process...");
    }

    @Override
    @SecuredByAspect(roles = {"ADMIN"})
    public double compute() {
        double data = 12;
        System.out.println("Business Compute...");
        return data;
    }
}
