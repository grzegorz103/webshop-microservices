/*
package service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String userId;

    private String street;

    private String city;

    private String postCode;

    private String houseNumber;

}
*/

package service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Document(indexName = "user-service", type = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address{

    @Id
    private String id;

    @NotNull
    private String userId;

    private String street;

    private String city;

    private String postCode;

    private String houseNumber;

}
