/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.keycloak.testsuite.pages;

import org.keycloak.services.resources.account.AccountFormService;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.ws.rs.core.UriBuilder;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
public class AccountTotpPage extends AbstractAccountPage {

    @FindBy(id = "totpSecret")
    private WebElement totpSecret;

    @FindBy(id = "totp")
    private WebElement totpInput;

    @FindBy(id = "userLabel")
    private WebElement totpLabelInput;

    @FindBy(css = "button[type=\"submit\"]")
    private WebElement submitButton;

    @FindBy(id = "remove-mobile")
    private WebElement removeLink;

    @FindBy(id = "mode-barcode")
    private WebElement barcodeLink;

    @FindBy(id = "mode-manual")
    private WebElement manualLink;

    private String getPath() {
        return AccountFormService.totpUrl(UriBuilder.fromUri(getAuthServerRoot())).build("test").toString();
    }

    public void configure(String totp) {
        totpInput.sendKeys(totp);
        submitButton.click();
    }

   public void configure(String totp, String userLabel) {
       totpInput.sendKeys(totp);
       totpLabelInput.sendKeys(userLabel);
       submitButton.click();
    }

    public String getTotpSecret() {
        return totpSecret.getAttribute("value");
    }

    public boolean isCurrent() {
        return driver.getTitle().contains("Account Management") && driver.getCurrentUrl().split("\\?")[0].endsWith("/account/totp");
    }

    public void open() {
        driver.navigate().to(getPath());
    }

    public void removeTotp() {
        removeLink.click();
    }

    public void clickManual() {
        manualLink.click();
    }

    public void clickBarcode() {
        barcodeLink.click();
    }

}
