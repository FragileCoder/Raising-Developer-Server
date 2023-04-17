package com.developer.domainmodel;


import java.util.UUID;

public record CharacterDomain(
        UUID id,
        int backend,
        int frontend,
        int ops,
        int health,
        int teamwork
        ) {
    public CharacterDomain(UUID id){
        this(id,0,0,0 ,0,0);
    }
    public CharacterDomain withBackend(int backend) {
        return new CharacterDomain(this.id, backend, this.frontend, this.ops, this.health, this.teamwork);
    }
    public CharacterDomain withFrontend(int frontend) {
        return new CharacterDomain(this.id, this.backend, frontend, this.ops, this.health, this.teamwork);
    }
    public CharacterDomain withOps(int ops) {
        return new CharacterDomain(this.id, this.backend, this.frontend, ops, this.health, this.teamwork);
    }
    public CharacterDomain withHealth(int health) {
        return new CharacterDomain(this.id, this.backend, this.frontend, this.ops, health, this.teamwork);
    }
    public CharacterDomain withTeamwork(int teamwork) {
        return new CharacterDomain(this.id, this.backend, this.frontend, this.ops, this.health, teamwork);
    }
}
