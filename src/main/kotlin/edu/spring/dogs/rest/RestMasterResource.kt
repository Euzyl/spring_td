package edu.spring.dogs.rest

import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path="masters", collectionResourceRel = "masters")
interface RestMasterResource<Master,Int> {

}