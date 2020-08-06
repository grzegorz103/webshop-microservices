package product.service.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import product.service.services.category.CategoryDTO;
import product.service.services.category.CategoryService;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Value("${url.category}")
    private String categoryApiUrl;

    @Test
    void getByIdTest() throws Exception {
        when(categoryService.getById(anyLong()))
                .thenReturn(new CategoryDTO());

        mockMvc.perform(get(categoryApiUrl + "/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllTest() throws Exception {
        when(categoryService.getAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(new CategoryDTO(), new CategoryDTO())));

        mockMvc.perform(get(categoryApiUrl))
                .andDo(print())
                .andExpect(status().isOk());
    }
/*
    @Test
    void createTest() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO(0L, "test", Collections.emptyList());
        when(categoryService.create(any(CategoryDTO.class)))
                .thenReturn(new CategoryDTO());

        mockMvc.perform(post(categoryApiUrl)
                .content(new ObjectMapper().writeValueAsString(categoryDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json"))
                .andDo(print())
                .andExpect(status().isCreated());
    }*/

}
