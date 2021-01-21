package Crypto;

public class LogInResult {
   private boolean isLogged;
   private String error;

   public boolean getIsLogged() {
      return this.isLogged;
   }

   public void setIsLogged(boolean isLogged) {
      this.isLogged = isLogged;
   }

   public String getError() {
      return this.error;
   }

   public void setError(String error) {
      this.error = error;
   }
}
