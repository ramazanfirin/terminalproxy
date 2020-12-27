package com.mastertek.terminalproxy.web.rest;

import static com.mastertek.terminalproxy.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.mastertek.terminalproxy.TerminalproxyApp;
import com.mastertek.terminalproxy.domain.Terminal;
import com.mastertek.terminalproxy.repository.TerminalRepository;
import com.mastertek.terminalproxy.service.WellcamDeviceService;
import com.mastertek.terminalproxy.service.dto.wellcam.WellcamUtil;
import com.mastertek.terminalproxy.web.rest.errors.ExceptionTranslator;
import com.mastertek.terminalproxy.web.rest.vm.AddPersonVM;
import com.mastertek.terminalproxy.web.rest.vm.DeletePersonVM;
import com.mastertek.terminalproxy.web.rest.vm.EditPersonVM;
import com.mastertek.terminalproxy.web.rest.vm.QueryVM;

/**
 * Test class for the TerminalResource REST controller.
 *
 * @see TerminalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TerminalproxyApp.class)
public class TerminalResourceIntTest {

    @Autowired
    private TerminalRepository terminalRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTerminalMockMvc;

    private Terminal terminal;

    @Autowired
    private WellcamDeviceService wellcamDeviceService;
    
    
    String customizeID = "10000";
    String path = "C:\\Users\\ramazan\\Desktop\\Face_733935_19121_1557049797507.jpg";
	String picInfo ;
	
	AddPersonVM addPersonVM=null;
	EditPersonVM editPersonVM=null;
	DeletePersonVM deletePersonVM=null;
	QueryVM queryPersonVM=null;
    
    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        final TerminalResource terminalResource = new TerminalResource(terminalRepository,wellcamDeviceService);
        this.restTerminalMockMvc = MockMvcBuilders.standaloneSetup(terminalResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
        
        
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Terminal createEntity(EntityManager em) {
        Terminal terminal = new Terminal();
        return terminal;
    }

    @Before
    public void initTest() throws Exception {
    	byte[] image = WellcamUtil.imageToByteArray(path);
        picInfo = WellcamUtil.encodeFileToBase64Binary(image);
       
        addPersonVM = new AddPersonVM();
        addPersonVM.setBrand("DarkFore");
        addPersonVM.setDeviceId("1368966");
        addPersonVM.setIdCardNo("430923199011044411");
        addPersonVM.setIp("192.168.1.123");
        addPersonVM.setPersonId("1");
        addPersonVM.setPersonName("Ramazan FIRIN");
        addPersonVM.setPicture(picInfo);
        addPersonVM.setPort("80");
        
        editPersonVM = new EditPersonVM();
        editPersonVM.setBrand("DarkFore");
        editPersonVM.setDeviceId("1368966");
        editPersonVM.setIdCardNo("430923199011044411");
        editPersonVM.setIp("192.168.1.123");
        editPersonVM.setPersonId("1");
        editPersonVM.setPersonName("Ramazan FIRIN -2 ");
        editPersonVM.setPicture(picInfo);
        editPersonVM.setPort("80");
        
        deletePersonVM = new DeletePersonVM();
        deletePersonVM.setBrand("DarkFore");
        deletePersonVM.setDeviceId("1368966");
        deletePersonVM.setIp("192.168.1.123");
        deletePersonVM.setPersonId("1");
        deletePersonVM.setPort("80");
    
        queryPersonVM = new QueryVM();
        queryPersonVM.setBrand("DarkFore");
        queryPersonVM.setDeviceId("1368966");
        queryPersonVM.setIp("192.168.1.123");
        queryPersonVM.setPort("80");
        queryPersonVM.setStartDate("2018-11-13T00:00:00");
        queryPersonVM.setEndDate("2021-11-13T23:59:59");
    }

    @Test
    @Transactional
    public void createTerminal() throws Exception {
        
    	MvcResult mvcresult = restTerminalMockMvc.perform(post("/api/terminals/addPerson")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addPersonVM)))
            .andExpect(status().isOk()).andReturn();

        String result = mvcresult.getResponse().getContentAsString();
        System.out.println(result);
    }
    
    @Test
    @Transactional
    public void createTerminal_Error() throws Exception {

//    	restTerminalMockMvc.perform(post("/api/terminals/addPerson")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(addPersonVM)))
//            .andExpect(status().isOk());

        MvcResult mvcResult = restTerminalMockMvc.perform(post("/api/terminals/addPerson")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(addPersonVM)))
                .andExpect(status().is5xxServerError()).andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        System.out.println();
    }


    @Test
    @Transactional
    public void updateTerminal() throws Exception {
                
        restTerminalMockMvc.perform(post("/api/terminals/editPerson")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(editPersonVM)))
            .andExpect(status().isOk());

       
    }

   

    @Test
    @Transactional
    public void deleteTerminal() throws Exception {
        
    	restTerminalMockMvc.perform(post("/api/terminals/deletePerson")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deletePersonVM)))
            .andExpect(status().isOk());

    }
    
    @Test
    @Transactional
    public void query() throws Exception {
       
    	MvcResult mvcResult = restTerminalMockMvc.perform(post("/api/terminals/query")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(queryPersonVM)))
            .andExpect(status().isOk()).andReturn();

    	String result = mvcResult.getResponse().getContentAsString();
    	System.out.println(result);
    }

    @Test
    @Transactional
    public void query_noData() throws Exception {
       
    	queryPersonVM.setStartDate("2021-11-13T00:00:00");
    	
    	MvcResult mvcResult = restTerminalMockMvc.perform(post("/api/terminals/query")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(queryPersonVM)))
            .andExpect(status().isOk()).andReturn();

    	String result = mvcResult.getResponse().getContentAsString();
    	//assertThat(result).isEqualTo("");
    }

    
    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Terminal.class);
        Terminal terminal1 = new Terminal();
        terminal1.setId(1L);
        Terminal terminal2 = new Terminal();
        terminal2.setId(terminal1.getId());
        assertThat(terminal1).isEqualTo(terminal2);
        terminal2.setId(2L);
        assertThat(terminal1).isNotEqualTo(terminal2);
        terminal1.setId(null);
        assertThat(terminal1).isNotEqualTo(terminal2);
    }
}
