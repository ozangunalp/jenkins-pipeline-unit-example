package net.courtanet.jenkins

import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library
import static org.junit.Assert.assertTrue

import org.junit.*
import org.junit.rules.TemporaryFolder

import com.lesfurets.jenkins.unit.cps.BasePipelineTestCPS
import com.lesfurets.jenkins.unit.global.lib.GitSource

class ExampleTest extends BasePipelineTestCPS {

    @ClassRule
    public static TemporaryFolder folder = new TemporaryFolder()

    static File temp

    @BeforeClass
    static void init() {
        temp = folder.newFolder('libs')
    }

    @Before
    void setup() {
        super.setUp()
        helper.registerSharedLibrary(library("commons")
                        .defaultVersion('master')
                        .allowOverride(true)
                        .targetPath(temp.path)
                        .retriever(GitSource.gitSource('git@gitlab.admin.courtanet.net:devteam/lesfurets-jenkins-shared.git'))
                        .build())
    }

    @Test
    void should_return_true() {
        loadScript('pipeline.jenkins')
        printCallStack()
        assertTrue(true)
    }

}