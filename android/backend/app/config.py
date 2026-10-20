from pydantic_settings import BaseSettings, SettingsConfigDict

class Settings(BaseSettings):
    database_url: str = "sqlite:///./tareia.db"
    redis_url: str = "redis://localhost:6379/0"
    google_client_id: str = ""
    google_client_secret: str = ""
    ai_provider: str = "mock"
    ai_api_key: str = ""
    model_config = SettingsConfigDict(env_file=".env", extra="ignore")

settings = Settings()
