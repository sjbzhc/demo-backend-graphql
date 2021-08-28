package com.demobackend.demo.domain.exceptions;

public class CourseRepositoryException extends DemoException {
    public static class RepositoryAccessException extends CourseRepositoryException {}
}
